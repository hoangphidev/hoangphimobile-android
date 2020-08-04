package tech.hoangphi.store.Models;

public class OrderResponse {
    private boolean error;
    private String message;
    private int orderid;

    public OrderResponse(boolean error, String message, int orderid) {
        this.error = error;
        this.message = message;
        this.orderid = orderid;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public int getOrderid() {
        return orderid;
    }
}
