package org.haulmont.fedoseew.ViewsAndForms.Bank;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.haulmont.fedoseew.Models.Bank;
import org.haulmont.fedoseew.Models.CreditOffer;
import org.haulmont.fedoseew.Services.*;
import org.haulmont.fedoseew.ViewsAndForms.MyUI;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringView(name = "Bank")
public class BankView extends VerticalLayout implements View {

    public static Grid<Bank> bankGrid = new Grid<>(Bank.class);
    public static long bank_id;
    private final Button addButton = new Button("Добавить");
    private final Button deleteButton = new Button("Удалить");
    private final Button viewCurrentCreditOffer = new Button("Детали кредита");
    @Autowired
    PaymentScheduleService paymentScheduleService;
    @Autowired
    private BankService bankService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CreditService creditService;
    @Autowired
    private CreditOfferService creditOfferService;

    static void updateBankGrid(BankService bankService) {
        bankGrid.setItems(bankService.findAll());
    }

    @PostConstruct
    void init() {
        bank_id = 0;
        MyUI.setStyleForButton(1);
        Page.getCurrent().setTitle("Bank");
        deleteButton.setEnabled(false);
        viewCurrentCreditOffer.setEnabled(false);
        deleteButton.setStyleName(ValoTheme.BUTTON_DANGER);
        deleteButton.setIcon(VaadinIcons.MINUS);
        addButton.setIcon(VaadinIcons.PLUS);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponents(addButton, viewCurrentCreditOffer, deleteButton);
        horizontalLayout.setSizeFull();
        horizontalLayout.setComponentAlignment(deleteButton, Alignment.TOP_RIGHT);
        horizontalLayout.setComponentAlignment(viewCurrentCreditOffer, Alignment.TOP_CENTER);
        addComponent(horizontalLayout);

        bankGrid.setSizeFull();
        bankGrid.setColumns("client", "creditInBank");
        bankGrid.setItems(bankService.findAll());

        addComponent(bankGrid);

        createButtonsListeners();
    }

    private void createButtonsListeners() {
        bankGrid.addSelectionListener(valueChangeEvent -> {
            if (!bankGrid.asSingleSelect().isEmpty()) {
                deleteButton.setEnabled(true);
                viewCurrentCreditOffer.setEnabled(true);
            } else {
                deleteButton.setEnabled(false);
                viewCurrentCreditOffer.setEnabled(false);
            }
        });

        addButton.addClickListener(e -> {
            Bank bank = new Bank();
            BankForm bankForm = new BankForm(bankService, bank, clientService, creditService);
            getUI().addWindow(bankForm);
        });


        viewCurrentCreditOffer.addClickListener(e -> {
            bank_id = bankGrid.asSingleSelect().getValue().getId();
            getUI().getNavigator().navigateTo("allOffers");
        });
        deleteButton.addClickListener(e -> {
            Bank bank = bankGrid.asSingleSelect().getValue();
            bank_id = bankGrid.asSingleSelect().getValue().getId();
            try {
                List<CreditOffer> creditOffers = creditOfferService.findAllOffersForClient(BankView.bank_id);
                List<Long> idsOfPaymentSchedules = new ArrayList<>();

                for (CreditOffer creditOffer : creditOffers)
                    idsOfPaymentSchedules.add(creditOffer.getPaymentSchedule().getId());

                creditOfferService.deleteAllOffersForClient(BankView.bank_id);

                for (Long l : idsOfPaymentSchedules)
                    paymentScheduleService.deleteById(l);

                bankService.delete(bank);
                updateBankGrid(bankService);
                Notification notification = new Notification(bank.toString() + " был успешно удален",
                        Notification.Type.WARNING_MESSAGE);
                notification.setDelayMsec(1500);
                notification.setPosition(Position.BOTTOM_CENTER);
                notification.show(getUI().getPage());
            } catch (Exception deleteException) {
                Notification notification = new Notification("Ошибка! Попробуйте еще раз позже",
                        Notification.Type.WARNING_MESSAGE);
                notification.show(getUI().getPage());
                deleteException.printStackTrace();
            }
        });
        updateBankGrid(bankService);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }
}