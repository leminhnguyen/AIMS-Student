package entity.invoice;

import entity.order.Order;

public class Invoice {

    private Order order;
    
    public Invoice(){

    }

    public Invoice(Order order){
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void saveInvoice(){
        
    }
}
