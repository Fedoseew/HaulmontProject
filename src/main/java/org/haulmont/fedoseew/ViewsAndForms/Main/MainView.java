package org.haulmont.fedoseew.ViewsAndForms.Main;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.haulmont.fedoseew.ViewsAndForms.MyUI;

import java.io.File;
import java.io.Serializable;

@SpringView(name = "")
public class MainView extends VerticalLayout implements View, Serializable {

    @PostConstruct
    void init() {
        MyUI.buttonsLayout.setVisible(false);
        FileResource bankImageResource = createFileResource("bank.png");
        FileResource creditImageResource = createFileResource("credit.png");
        FileResource clientImageResource = createFileResource("clients.png");
        FileResource offerImageResource = createFileResource("offer.png");

        HorizontalLayout mainLayout = new HorizontalLayout();
        VerticalLayout bankLayout = new VerticalLayout();
        VerticalLayout creditLayout = new VerticalLayout();
        VerticalLayout clientsLayout = new VerticalLayout();
        VerticalLayout offerLayout = new VerticalLayout();

        Image bank = new Image("", bankImageResource);
        Image credit = new Image("", creditImageResource);
        Image clients = new Image("", clientImageResource);
        Image offer = new Image("", offerImageResource);

        bank.setWidth("70%");
        credit.setWidth("70%");
        clients.setWidth("70%");
        offer.setWidth("70%");

        Label bankLabel = new Label("Банк");
        bankLabel.addStyleName(ValoTheme.LABEL_BOLD);
        bankLabel.addStyleName(ValoTheme.LABEL_COLORED);

        Label creditLabel = new Label("Кредиты");
        creditLabel.addStyleName(ValoTheme.LABEL_BOLD);
        creditLabel.addStyleName(ValoTheme.LABEL_COLORED);

        Label clientsLabel = new Label("Клиенты");
        clientsLabel.addStyleName(ValoTheme.LABEL_BOLD);
        clientsLabel.addStyleName(ValoTheme.LABEL_COLORED);

        Label offerLabel = new Label("Оформить кредит");
        offerLabel.addStyleName(ValoTheme.LABEL_BOLD);
        offerLabel.addStyleName(ValoTheme.LABEL_COLORED);

        bankLayout.addComponents(bank, bankLabel);
        bankLayout.setComponentAlignment(bankLabel, Alignment.TOP_CENTER);
        bankLayout.setComponentAlignment(bank, Alignment.TOP_CENTER);
        bankLayout.addLayoutClickListener(event -> {
            getUI().getNavigator().navigateTo("Bank");
            MyUI.buttonsLayout.setVisible(true);
        });
        bankLayout.addStyleName(ValoTheme.LAYOUT_WELL);

        creditLayout.addComponents(credit, creditLabel);
        creditLayout.setComponentAlignment(creditLabel, Alignment.TOP_CENTER);
        creditLayout.setComponentAlignment(credit, Alignment.TOP_CENTER);
        creditLayout.addLayoutClickListener(event -> {
            getUI().getNavigator().navigateTo("Credits");
            MyUI.buttonsLayout.setVisible(true);
        });
        creditLayout.addStyleName(ValoTheme.LAYOUT_WELL);

        clientsLayout.addComponents(clients, clientsLabel);
        clientsLayout.setComponentAlignment(clientsLabel, Alignment.TOP_CENTER);
        clientsLayout.setComponentAlignment(clients, Alignment.TOP_CENTER);
        clientsLayout.addLayoutClickListener(event -> {
            getUI().getNavigator().navigateTo("Clients");
            MyUI.buttonsLayout.setVisible(true);
        });
        clientsLayout.addStyleName(ValoTheme.LAYOUT_WELL);

        offerLayout.addComponents(offer, offerLabel);
        offerLayout.setComponentAlignment(offerLabel, Alignment.TOP_CENTER);
        offerLayout.setComponentAlignment(offer, Alignment.TOP_CENTER);
        offerLayout.addLayoutClickListener(event -> {
            getUI().getNavigator().navigateTo("CreditOffer");
            MyUI.buttonsLayout.setVisible(true);
        });
        offerLayout.addStyleName(ValoTheme.LAYOUT_WELL);

        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        mainLayout.setWidth("100%");
        mainLayout.addComponents(bankLayout, creditLayout, clientsLayout, offerLayout);
        addComponent(mainLayout);
    }

    private FileResource createFileResource (String fileName) {
        return new FileResource(new File("src/main/resources/images/"+fileName));
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }
}
