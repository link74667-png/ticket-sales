package exception;

// Собственное исключение: билеты закончились
public class SoldOutException extends Exception {
    public SoldOutException(String message) {
        super(message);
    }
}
