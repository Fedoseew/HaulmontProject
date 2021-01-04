package org.haulmont.fedoseew.ViewsAndForms.CreditOffer;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.haulmont.fedoseew.Models.Client;
import org.haulmont.fedoseew.Models.Credit;
import org.haulmont.fedoseew.Services.ClientService;
import org.haulmont.fedoseew.Services.CreditService;
import org.haulmont.fedoseew.ViewsAndForms.MyUI;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Arrays;


@SpringView(name = "CreditOffer")
public class CreditOfferView extends VerticalLayout implements View {
    HorizontalLayout mainLayout = new HorizontalLayout();

    private NativeSelect<Client> clientNativeSelect;
    private NativeSelect<Credit> creditNativeSelect;
    private final NativeSelect<Integer> creditPeriod = new NativeSelect<>
            ("Срок кредита (лет)", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

    @Autowired
    private ClientService clientService;
    @Autowired
    private CreditService creditService;

    private final TextField creditAmount = new TextField("Сумма кредита");



    @PostConstruct
    void init() {
        MyUI.setStyleForButton(4);
        clientNativeSelect = new NativeSelect<>("Клиент", clientService.findAll());
        //creditNativeSelect = new NativeSelect<>("Кредит", creditService.findAll());
        creditAmount.setRequiredIndicatorVisible(true);
        creditAmount.setPlaceholder("1 000 000");
        creditPeriod.setSelectedItem(1);
        mainLayout.addComponents(creditAmount, creditPeriod, clientNativeSelect);
        mainLayout.setWidth("100%");
        mainLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        addComponents(mainLayout);
        setSizeFull();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }
}
