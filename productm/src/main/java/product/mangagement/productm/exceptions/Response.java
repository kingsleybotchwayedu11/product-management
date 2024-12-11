package product.mangagement.productm.exceptions;

public class Response {
    String message;
    int statusCode;
    public void setMessage(String message) {
        this.message = message;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    Response(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
    public String getMessage() {
        return message;
    }
    public int getStatusCode() {
        return statusCode;
    }
}
