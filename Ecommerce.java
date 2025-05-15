import java.util.*;

class User {
    private String username;
    private String password;
    private String phoneNumber;
    private String email;

    // User class constructor;
    public User(String username, String password, String phoneNumber, String email) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // getter and setter methods
    public String getUsername() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}

// UserManager class for login user's creadentials
class UserManager {
    private HashMap<String, User> usersCredential; 
    private Scanner sc;
    private boolean isLoggedIn = false;
    private User currentUser = null;

    // UserManager class constructor
    public UserManager() {
        this.usersCredential = new HashMap<>();
        this.sc = new Scanner(System.in);
    }
    
    // Method for user input 
    public String getUserInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if(input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while(input.isEmpty());

        return input;

    }

    public boolean isValidUsername(String username) {
        if (!username.matches("^[a-zA-Z][a-zA-Z0-9_]{3,14}$")) {
            System.out.println("Invalid username. It should:");
            System.out.println("- Start with a letter");
            System.out.println("- Be 4 to 15 characters long");
            System.out.println("- Only contain letters, numbers, and underscores");
            return false;
        }

        return true;
    }
    
    public boolean isValidPassword(String password) {
        if (password.length() < 8 || password.length() > 12) {
            System.out.println("❌ Password must be between 8 and 12 characters.");
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            System.out.println("❌ Password must contain at least one lowercase letter.");
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            System.out.println("❌ Password must contain at least one uppercase letter.");
            return false;
        }
        if (!password.matches(".*\\d.*")) {
            System.out.println("❌ Password must contain at least one digit.");
            return false;
        }
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            System.out.println("❌ Password must contain at least one special character.");
            return false;
        }

        return true;

    }

    public static boolean isValidPhoneNumber(String number) {
        // Allow optional '+' at the start, followed by 8 to 15 digits
        return number.matches("^\\+?[0-9]{8,15}$");
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.+-]+@gmail\\.com$";

        if(!email.matches(emailRegex)) {
            System.out.println("Invalid email. Only Gmail addresses are allowed (e.g., username@gmail.com)");
            return false;
        }

        return true;
    }

    // Method for user registration
    public void userRegistration() {
        boolean isValid;
        String username;
        do {
            username = getUserInput("Enter username: ");
            isValid = isValidUsername(username);
        } while(!isValid);

        String password;
        do {
            password = getUserInput("Enter password: ");
            isValid = isValidPassword(password);
        } while(!isValid);

        String phoneNumber;
        do {
            phoneNumber = getUserInput("Enter phone number (8-15 digits, optional +): ");
            if(!isValidPhoneNumber(phoneNumber)) {
                System.out.println("Invalid number. Please enter a valid international phone number.");
            }
        } while (!isValidPhoneNumber(phoneNumber));

        String email;
        do {
            email = getUserInput("Enter email: ");
            isValid = isValidEmail(email);
        } while(!isValid);
        
        User user = new User(username, password, phoneNumber, email);
        usersCredential.put(username, user);
        System.out.println("user registered successfuly!");
    }

    // Method for user login
    public User userLogin() {
        String identifier = getUserInput("Enter email or phone number: ");
        String password = getUserInput("Enter password: ");

        for(User user : usersCredential.values()) {
            if((user.getEmail().equals(identifier) || user.getPhoneNumber().equals(identifier)) && user.getPassword().equals(password)) {
                System.out.println("✅ Login successful. Welcome, " + user.getUsername());
                isLoggedIn = true;
                currentUser = user;
                return user;
            }
        }

        return null;
    }

    // Method for reset password
    public void forgetPassword() {
        String username = getUserInput("Enter username to reset password: ");

        if(usersCredential.containsKey(username)) {
            User user = usersCredential.get(username);

            String oldPassword = getUserInput("Enter old password: ");
            if(user.getPassword().equals(oldPassword)) {
                String newPassword = getUserInput("Enter new password: ");
                if(newPassword.equals(oldPassword)) {
                    System.out.println("❌ New password cannot be same as old password");
                    return;
                } 

                user.setPassword(newPassword);
                System.out.println("✅ Password reset successfuly!");
            } else {
                System.out.println("❌ Incorrect old password.");
            }
        } else {
            System.out.println("❌ Username not found.");
        }
    }

    public void logout() {
        if(isLoggedIn && currentUser != null) {
            System.out.println("User " + currentUser.getUsername() + " logged out successfully!");
            isLoggedIn = true;
            currentUser = null;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }
}

// Product class
class Product {
    private String productName;
    private double price;
    private String category;
    private String description;
    private int quantity;
    private int id;

    // Product class constructor
    public Product(String productName, double price, String category, String description, int quantity, int id) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.description = description;
        this.quantity = quantity;
        this.id = id;
    }

    // getter and setter methods
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    } 

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID: " + id +
               " | Name: " + productName +
               " | Description: " + description +
               " | Quantity: " + quantity +
               " | Price: $" + price +
               " | Category: " + category;
    }
}

// Store class for products
class Store {
    private HashMap<Integer, Product> products;
    private Scanner sc;
    private Cart cart;
    private List<Order> orderHistory;
   
    // Store class constructor
    public Store() {
        this.products = new HashMap<>();
        this.sc = new Scanner(System.in);
        this.cart = new Cart();
        this.orderHistory = new ArrayList<>();

        addProduct("Laptop", 1500.50, "Electronics", "High performance laptop", 10, 101);
        addProduct("Smartphone", 800.00, "Electronics", "Latest model smartphone", 20, 150);
        addProduct("Coffie Mug", 12.50, "Kitchenware", "Ceramic coffie mug", 50, 50);
        addProduct("Headphones", 150.00, "Electronics", "Noise-cancelling headphones", 15, 5);
    }

    // method for cart 
    public Product getProductById(int id) {
        return products.get(id);
    }

    // method for adding product
    public void addProduct(String productName, double price, String category, String description, int quantity, int id) {
        Product product = new Product(productName, price, category, description, quantity, id);
        products.put(id, product);
        System.out.println("Product '" + productName + "' added successfuly!");
    }

    // method for taking user input string type
    public String getUserInput(String prompt) {
        String input;
        do {
            System.out.print(prompt); 
            input = sc.nextLine().trim();
            if(input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while(input.isEmpty());

        return input;

    }

    // method for taking user input int type
    public int getIntUserInput(String prompt) {
        int input =  0;
        boolean valid = false;

        while(!valid) {
            try {
                System.out.print(prompt);
                input = sc.nextInt();
                valid = true;
            } catch(InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine();
            }
        }

        return input;

    }

    // method for taking user input double type
    public double getDoubleUserInput(String prompt) {
        double input = 0;
        boolean valid = false;

        while(!valid) {
            try {
                System.out.print(prompt);
                input = sc.nextDouble();
                valid = true;
            } catch(InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine();
            }
        }

        return input;

    }

    // method for view products
    public void viewProducts() {
        if(products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("\n=== Available Products ===");
            for(Product product : products.values()) {
                System.out.println(product);
            }
        }
    }

    // method for search products
    public void searchProduct(int id) {
        if(products.containsKey(id)) {
            System.out.println("\n=== Product found ===");
            System.out.println(products.get(id));
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }

    // method for adding products int store
    public void addProductToStore() {
        int id = getIntUserInput("Enter product ID to add your product in store: ");
        String productName = getUserInput("Enter product name: ");
        String description = getUserInput("Enter description: ");
        int quantity = getIntUserInput("Enter quantity: ");
        double price = getDoubleUserInput("Enter price: ");
        String category = getUserInput("Enter category: ");

        addProduct(productName, price, category, description, quantity, id);
        System.out.println("Product added successfully!");
    }

    // method for edit products (Admin)
    public void editProduct() {
        int id = getIntUserInput("Enter product ID to edit: ");
        if(products.containsKey(id)) {
            String newName = getUserInput("Enter new product name: ");
            double newPrice = getDoubleUserInput("Enter new price: ");
            String newCategory = getUserInput("Enter new category: ");
            String newDescription = getUserInput("Enter new description: ");
            int newQuantity = getIntUserInput("Enter new quantity: ");
            
            Product product = products.get(id);
            product.setProductName(newName);
            product.setPrice(newPrice);
            product.setCategory(newCategory);
            product.setDescription(newDescription);
            product.setQuantity(newQuantity);
            System.out.println("Product with ID " + id + " has been updated.");
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }

    // method for delete products (Admin)
    public void deleteProduct(int id) {
        if(products.containsKey(id)) {
            products.remove(id);
            System.out.println("Product with ID " + id + " has been deleted.");
        } else {
            System.out.println("Product with ID " + id + " not found.");
        }
    }

    // method for sort products
    public void sortProduct() {
        System.out.println("\n=== Sort products ===");
        System.out.println("1. Sort by Name");
        System.out.println("2. Sort by Price");
        System.out.println("3. Sort by Quantity");
        System.out.println("4. Sort by Category");
        
        int choice = getIntUserInput("Choose an option: ");
        List<Product> list = new ArrayList<>(products.values()); // using list for sorting
        switch(choice) {
            case 1:
                list.sort(Comparator.comparing(Product::getProductName));
                break;
            case 2:
                list.sort(Comparator.comparingDouble(Product::getPrice));
                break;
            case 3:
                list.sort(Comparator.comparingInt(Product::getQuantity));
                break;
            case 4:
                list.sort(Comparator.comparing(Product::getCategory));
                break;
            default:
                System.out.println("Invalid choice for sorting.");
        }
         
        System.out.println("\n=== Sort products ===");
        list.forEach(System.out::println);

    }

    // method for adding product to store (Admin)
    public void addProductToCart() {
        int productId = getIntUserInput("Enter product ID to add to cart: ");
        int quantity = getIntUserInput("Enter quantity: ");

        if(products.containsKey(productId)) {
            cart.addToCart(productId, quantity);
        } else {
            System.out.println("product not found.");
        }
    } 

    // method for viewing cart
    public void viewCart() {
        Map<Integer, Integer> cartItems = cart.getCartItems();
        if(cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("\n=== Cart Item ===");
            for(Map.Entry<Integer, Integer> entry : cartItems.entrySet()) {
                int id = entry.getKey();
                int quantity = entry.getValue();

                Product product = products.get(id);
                int newQuantity = product.getQuantity() - quantity;
                product.setQuantity(newQuantity);

                if(product != null) {
                    double price = product.getPrice() * quantity;
                    System.out.println(product.getProductName() +  " | Qty: " + quantity + " | Subtotal: $" + price);
                }

            }
        }
    }

    // method for payment
    public boolean processMockPayment(double amount) {
        System.out.println("\n=== Paymant ===");
        System.out.println("Total amount to be paid: $" + amount);
        String confirmation = getUserInput("Type 'pay' to confirm payment: ");

        if(confirmation.equalsIgnoreCase("pay")) {
            System.out.println("Payment successful!");
            return true;
        } else {
            System.out.println("Payment cancelled.");
            return false;
        }
    }

    // method for checkout
    public void checkOut() {
        HashMap<Integer, Integer> cartItems = new HashMap<>(cart.getCartItems());
        if(cartItems.isEmpty()) {
            System.out.println("Cart is empty. Nothing to checkout.");
            return;
        }

        double total = 0;
        for(Map.Entry<Integer, Integer> entry : cartItems.entrySet()) {
            int id = entry.getKey();
            int qty = entry.getValue();

            Product product = products.get(id);
            if(product != null) {
                double price = product.getPrice() * qty;
                total += price;
                System.out.println(product.getProductName() +  " | Qty: " + qty + " | Subtotal: $" + price);
            }
        }

        boolean paymentSuccess = processMockPayment(total);
        if(paymentSuccess) {
            Order order = new Order(cartItems, total);
            orderHistory.add(order);
            System.out.println("Checkout successful! Total amount: $" + total);
            cart.clearCart(); // <--- clear the cart here
        }
    }

    // method for view order history
    public void viewOrderHistory() {
        if(orderHistory.isEmpty()) {
            System.out.println("No order have been placed yet.");
            return;
        }

        int count = 1;
        for(Order order : orderHistory) {
            System.out.println("\n=== Order #" + count + " ===");
            System.out.println("Date: " + order.getOrderDate());
            for(Map.Entry<Integer, Integer> entry : order.getItems().entrySet()) {
                int productId = entry.getKey();
                int quantity = entry.getValue();

                Product product = products.get(productId);
                if(product != null) {
                    double price = product.getPrice() * quantity;
                    System.out.println(product.getProductName() +  " | Qty: " + quantity + " | Subtotal: $" + price);
                }
            }

            System.out.println("Total Amount: " + order.getTotalAmount());
            count++;
        }
    }

    // method for delete product history
    public void deleteOrderHistory() {
        if(orderHistory.isEmpty()) {
            System.out.println("No order have been placed to delete history.");
        } else {
            String confirm = getUserInput("Are you sure you want to delete order history? (yes/no): ");
            if(confirm.equalsIgnoreCase("yes")) {
                orderHistory.clear();
                System.out.println("Order history deleted successfully.");
            } else {
                System.out.println("Order history not deleted.");
            }
        }
    }

    // method for placing order
    public void placeOrder() {
        if(cart.getCartItems().isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        viewCart();
        String confirm = getUserInput("Do you want to place the order? (yes/no): ");
        if(confirm.equalsIgnoreCase("yes")) {
            checkOut();
        } else {
            System.out.println("Order cancelled.");
        }

    }
}

// Cart class
class Cart {
    private HashMap<Integer, Integer> cartItems;

    // Cart class constructor
    public Cart() {
        this.cartItems = new HashMap<>();
    }

    // Add item to cart
    public void addToCart(int productId, int quantity) {
        cartItems.put(productId, cartItems.getOrDefault(productId, 0) + 1);
        System.out.println("Added product ID " + productId + " (Qty: " + quantity + ") to cart.");
    }

    // Remove item from cart
    public void removeFromCart(int productId) {
        if(cartItems.containsKey(productId)) {
            cartItems.remove(productId);
            System.out.println("Removed product ID " + productId + " from cart.");
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    // method to get cart items
    public Map<Integer, Integer> getCartItems() {
        return cartItems;
    }

    // method for clear cart items
    public void clearCart() {
        cartItems.clear();
    }
}

// Order class to track order history
class Order {
    private HashMap<Integer, Integer> items;
    private double totalAmount;
    private Date orderDate;

    // Order class constructor
    public Order(HashMap<Integer, Integer> items, double totalAmount) {
        this.items = new HashMap<>(items);
        this.totalAmount = totalAmount;
        this.orderDate = new Date();
    }

    public HashMap<Integer, Integer> getItems() {
        return items;
    }

    public double getTotalAmount() { 
        return totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }
}

public class Ecommerce {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserManager manager = new UserManager();
        Store store = new Store();
        boolean running = true;
        User loggedInUser = null;

        while(running) {
            System.out.println("\n=== Welcome to Ecommerce App ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Forget Password");
            System.out.println("4. Logout");
            System.out.println("5. View Products");
            System.out.println("6. Search Products");
            System.out.println("7. Add Product to Store (Admin)");
            System.out.println("8. Edit Product (Admin)");
            System.out.println("9. Delete product (Admin)");
            System.out.println("10. Sort Product");
            System.out.println("11. Add Product to Cart");
            System.out.println("12. View Cart");
            System.out.println("13. Checkout");
            System.out.println("14. View Order History");
            System.out.println("15. Delete Order History");
            System.out.println("16. Place Order");
            System.out.println("17. Exit");

            try {
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch(choice) {
                    case 1:
                        manager.userRegistration();
                        break;
                    case 2:
                        loggedInUser = manager.userLogin();
                        if(loggedInUser != null) {
                            System.out.println("Welcome back, " + loggedInUser.getUsername());
                        } else {
                            System.out.println("Invalid credentails.");
                        }
                        break;
                    case 3:
                        manager.forgetPassword();
                        break;
                    case 4:
                        manager.logout();
                        break;
                    case 5:
                        store.viewProducts();
                        break;
                    case 6:
                        System.out.print("Enter product ID to search: ");
                        int searchId = sc.nextInt();
                        store.searchProduct(searchId);
                        break;
                    case 7:
                        store.addProductToStore();
                        break;
                    case 8:
                        store.editProduct();
                        break;
                    case 9:
                        System.out.println("Enter product ID to delete: ");
                        int deleteId = sc.nextInt();
                        store.deleteProduct(deleteId);
                        break;
                    case 10:
                        store.sortProduct();
                        break;
                    case 11:
                        store.addProductToCart();
                        break;
                    case 12:
                        store.viewCart();
                        break;
                    case 13:
                        store.checkOut();
                        break;
                    case 14:
                        store.viewOrderHistory();
                        break;
                    case 15:
                        store.deleteOrderHistory();
                        break;
                    case 16:
                        store.placeOrder();
                        break;
                    case 17:
                        System.out.println("Thanks for using our Ecommerce App!");
                        return;
                    default:    
                        System.out.println("Invalid option. Please try again.");
                        
                }
            } catch(InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine();
            }
        }

        sc.close();
        
    } 
}