package org.haulmont.fedoseew.ViewsAndForms.CreditOffer;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.haulmont.fedoseew.Models.Client;
import org.haulmont.fedoseew.Models.Credit;
import org.haulmont.fedoseew.Services.*;
import org.haulmont.fedoseew.ViewsAndForms.MyUI;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Arrays;


@SpringView(name = "CreditOffer")
public class CreditOfferView extends VerticalLayout implements View, Serializable {
    HorizontalLayout headerLayout = new HorizontalLayout();
    VerticalLayout mainLayout = new VerticalLayout();

    static NativeSelect<Client> clientNativeSelect;
    private NativeSelect<Credit> creditNativeSelect;
    private final NativeSelect<Integer> creditPeriod = new NativeSelect<>
            ("Срок", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    private CheckBox registerNewUserCheckBox;

    @Autowired
    private ClientService clientService;
    @Autowired
    private CreditService creditService;
    @Autowired
    private PaymentScheduleService scheduleService;
    @Autowired
    CreditOfferService creditOfferService;
    @Autowired
    BankService bankService;

    private final TextField creditAmount = new TextField("Сумма кредита");

    @PostConstruct
    void init() {
        MyUI.setStyleForButton(4);
        Page.getCurrent().setTitle("CreditOffer");
        Label header = new Label("Оформление кредита");
        header.addStyleName(ValoTheme.LABEL_H2);
        headerLayout.setWidth("100%");
        headerLayout.addComponent(header);
        headerLayout.setComponentAlignment(header, Alignment.TOP_CENTER);
        headerLayout.addStyleName(ValoTheme.LAYOUT_CARD);
        clientNativeSelect = new NativeSelect<>("Клиент", clientService.findAllSort());
        clientNativeSelect.setRequiredIndicatorVisible(true);
        registerNewUserCheckBox = new CheckBox();
        registerNewUserCheckBox.setCaption("Клиента нет в списке");
        registerNewUserCheckBox.addValueChangeListener(valueChangeEvent -> {
            if(registerNewUserCheckBox.getValue()) {
                RegistrationUserForm registrationUserForm = new RegistrationUserForm(clientService);
                getUI().addWindow(registrationUserForm);
                registerNewUserCheckBox.setValue(false);
            }
        });
        HorizontalLayout amountAndPeriodLayout = new HorizontalLayout(creditAmount, creditPeriod);
        amountAndPeriodLayout.setHeight("100px");
        registerNewUserCheckBox.setHeight("100px");
        creditAmount.setRequiredIndicatorVisible(true);
        creditAmount.setPlaceholder("1 000 000");
        creditPeriod.setSelectedItem(1);
        creditPeriod.setRequiredIndicatorVisible(true);

        Button getCreditOffer = new Button("Подобрать кредит");
        getCreditOffer.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        getCreditOffer.addClickListener(clickEvent -> {
            try {
                CreditOfferForm creditOfferForm = new CreditOfferForm(creditService,
                        Long.parseLong(creditAmount.getValue()), creditPeriod.getValue(),
                        clientNativeSelect.getValue(), creditOfferService, scheduleService, bankService);
                getUI().addWindow(creditOfferForm);
            }catch (Exception e) {
                Notification error = new Notification("Ошибка! Проверьте корректность введеных данных");
                error.setDelayMsec(1500);
                error.show(getUI().getPage());
            }
        });

        mainLayout.addComponents(clientNativeSelect, registerNewUserCheckBox, amountAndPeriodLayout, getCreditOffer);
        addComponents(headerLayout, mainLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
