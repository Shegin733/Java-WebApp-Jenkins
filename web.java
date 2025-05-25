import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class web {
    public static void main(String[] args) throws IOException {
        // Start an HTTP server
        HttpServer server = HttpServer.create(new InetSocketAddress("172.174.1.112", 9090), 0);
        server.createContext("/start", new StartSwingHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server running on http://172.174.1.112:9090/start");
    }
    
    static class StartSwingHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            startSwingApp();
            String response = "Swing UI opened!";
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.close();
        }
        
        private void startSwingApp() {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Styled Java Application");
                frame.setSize(500, 400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().setBackground(new Color(30, 30, 30)); // Dark background

                JLabel label = new JLabel("Welcome to MyApp!", SwingConstants.CENTER);
                label.setFont(new Font("Arial", Font.BOLD, 20));
                label.setForeground(Color.WHITE); // White text

                JButton button = new JButton("Click Me");
                button.setFont(new Font("SansSerif", Font.PLAIN, 16));
                button.setBackground(new Color(50, 150, 250)); // Blue button
                button.setForeground(Color.WHITE);
                button.setFocusPainted(false);
                button.setBorderPainted(false);
                button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Hello from MyApp!", "Message", JOptionPane.INFORMATION_MESSAGE));

                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setBackground(new Color(30, 30, 30));
                panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
                panel.add(label);
                panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacing
                panel.add(button);

                frame.add(panel);
                frame.setVisible(true);
            });
        }
    }
}