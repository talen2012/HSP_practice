public class Homework03 {
    public static void main(String[] args) {
        Book book = new Book(35.0);
        System.out.println("书原来的价格：" + book.price);
        book.updatePrice();
        System.out.println("书更新后的价格：" + book.price);
    }
}

class Book {
    double price;
    public Book(double price) {
        this.price = price;
    }
    public void updatePrice() {
        if (this.price > 150) {
            this.price = 150;
        } else if (this.price > 100) {
            this.price = 100;
        }
    }
}
