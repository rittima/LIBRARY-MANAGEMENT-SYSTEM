package library;

public class BorrowedBook {
    private int bookId;
    private String bookName;
    private String author;
    private String borrowedDate;
    private String returnDate; // Nullable initially

    // Constructor
    public BorrowedBook(int i, String bookName, String author, String borrowedDate, String returnDate) {
        this.bookId = i;
        this.bookName = bookName;
        this.author = author;
        this.borrowedDate = borrowedDate;
        this.returnDate = returnDate;
    }


	// Getters and Setters
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getBorrowedDate() { return borrowedDate; }
    public void setBorrowedDate(String borrowedDate) { this.borrowedDate = borrowedDate; }

    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
}
