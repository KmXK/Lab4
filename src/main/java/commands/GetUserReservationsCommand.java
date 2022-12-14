package commands;

import service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetUserReservationsCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) {
        try {
            int userId = (int) req.getSession().getAttribute("userId");
            OrderService service = new OrderService();
            req.getSession().setAttribute("orders", service.getUserOrders(userId));
            if((int)req.getSession().getAttribute("userRole") == 1){
                req.getRequestDispatcher("adminPage.jsp").forward(req, res);
            }
            else{
                req.getRequestDispatcher("userPage.jsp").forward(req, res);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
