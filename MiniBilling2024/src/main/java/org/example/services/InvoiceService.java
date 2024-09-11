package org.example.services;

import org.example.Measurement;
import org.example.entities.*;
import org.example.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InvoiceService {

    private UserRepository userRepository;

    private ReadingRepository readingRepository;

    private PriceChartRepository priceChartRepository;

    private UserService userService;

    private InvoiceRepository invoiceRepository;
    private LineRepository lineRepository;
    private Measurement measurement = new Measurement();

    private StandingChargeService standingChargeService;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, UserService userService, PriceChartRepository priceChartRepository,
                          ReadingRepository readingRepository, UserRepository userRepository, LineRepository lineRepository,
                          StandingChargeService standingChargeService){
        this.invoiceRepository = invoiceRepository;
        this.userService = userService;
        this.readingRepository = readingRepository;
        this.priceChartRepository = priceChartRepository;
        this.userRepository = userRepository;
        this.lineRepository = lineRepository;
        this.standingChargeService = standingChargeService;
    }


    public void createInvoices(){
        List<Reading> readingsList = readingRepository.findAll();
        Collections.sort(readingsList, Comparator.comparingLong(r -> r.getUser().getClientId()));
        for (int i = 0; i < readingsList.size() - 1; i++){
            Invoice invoice = new Invoice();
            User user = userService.getUserByClientId(userRepository.findAll(), readingsList.get(i));
            List<Line> lines = new ArrayList<>();

            if (readingsList.get(i).getUser().getClientId() == (readingsList.get(i + 1).getUser().getClientId())){

               invoice.setDate(readingsList.get(i).getDate());
               invoice.setUser(user);
               int quantity = (int) (Math.ceil(readingsList.get(i + 1).getValue() - readingsList.get(i).getValue()));
               Line line = new Line(1, quantity, quantity * user.getPriceChart().getPrice());
               lines.add(line);
               invoice.setLines(lines);
               StandingCharge standingCharge = standingChargeService.createStandingCharge
                       (readingsList.get(i), readingsList.get(i + 1), 1.6);
               standingChargeService.save(standingCharge);
               invoice.setStandingCharge(standingCharge);

               double amountWithoutVat = measurement.calculateAmount(quantity,
                       readingsList.get(i), readingsList.get(i + 1), priceChartRepository.findAll());
               invoice.setAmountWithoutVat(amountWithoutVat);
               invoice.setVat(Math.round(0.2 * amountWithoutVat * 100) / 100.0);
               invoice.setFinalAmount(Math.round(100 * (amountWithoutVat + invoice.getVat() + invoice.getStandingCharge().getAmountWithVat())) / 100.0);
               Optional<Invoice> checkInvoice = invoiceRepository.findAllByDate(invoice.getDate());
               if (checkInvoice.isEmpty()){
                   lineRepository.saveAllAndFlush(lines);
                   invoiceRepository.saveAndFlush(invoice);
               }

            }
        }

    }

    public List<Invoice> getInvoicesOfUser(Long id){
        return invoiceRepository.findAllByUserClientId(id);
    }

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }
}
