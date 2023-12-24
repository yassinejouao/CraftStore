package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ClientDaoImpl;
import dao.IClientDao;
import dao.IOrderDao;
import dao.IOrderitemDao;
import dao.IProductDao;
import dao.OrderDaoImpl;
import dao.OrderItemDaoImpl;
import dao.ProductDaoImpl;
import metier.entities.Client;
import metier.entities.Order;
import metier.entities.Orderitem;
import metier.entities.Product;
import metier.entities.Status;
import metier.models.AddOrderModel;
import metier.models.OrderModel;
import metier.models.orderDetailsModel;
import metier.models.receivedData;
import metier.models.receivedData.orderData;

@WebServlet(name = "cos", urlPatterns = { "*.order" })
public class ControllerOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IOrderDao orderDao;
	private IOrderitemDao orderitemDao;
	private IClientDao clientDao;
	private IProductDao productDao;

	@Override
	public void init() throws ServletException {
		orderDao = new OrderDaoImpl();
		orderitemDao = new OrderItemDaoImpl();
		clientDao = new ClientDaoImpl();
		productDao = new ProductDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		// index order page
		if (path.equals("/index.order")) {
			OrderModel orderModel = new OrderModel();
			List<Order> orders = orderDao.getAllOrders();
			orderModel.setOrders(orders);
			req.setAttribute("ordersModel", orderModel);
			Status[] statusValues = Status.values();
			req.setAttribute("statusValues", statusValues);
			req.getRequestDispatcher("orders.jsp").forward(req, resp);
		}
		// add Order
		else if (path.equals("/add.order")) {
			AddOrderModel addOrderModel = new AddOrderModel();
			List<Client> clients = clientDao.searchClientByKeyword("%%");
			List<Product> products = productDao.searchProductByKeyWord("%%");
			addOrderModel.setClients(clients);
			addOrderModel.setProducts(products);
			req.setAttribute("addOrderModel", addOrderModel);
			req.getRequestDispatcher("addOrder.jsp").forward(req, resp);
		}
		// save order in database
		else if (path.equals("/save.order") && req.getMethod().equals("POST")) {
			BufferedReader reader = req.getReader();
			StringBuilder requestData = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				requestData.append(line);
			}
			ObjectMapper objectMapper = new ObjectMapper();
			receivedData rd = objectMapper.readValue(requestData.toString(), receivedData.class);
			Long idClient = Long.parseLong(rd.getIdClient());
			Double totalorder = 0.0;
			List<orderData> orderData = rd.getOrder();
			Order order = orderDao.save(new Order(idClient, new Date(), totalorder, Status.INPROGRESS));
			for (orderData od : orderData) {
				Product p = productDao.getProductByName(od.getProductName());
				Double totalProduct = p.getPriceProduct() * Integer.parseInt(od.getQuantity());
				Long quantity = 1 * Long.parseLong(od.getQuantity());
				orderitemDao.save(new Orderitem(order.getIdOrder(), p.getIdProduct(), quantity, totalProduct));
				totalorder += totalProduct;
				p.setStock(p.getStock() - quantity);
				productDao.updateProduct(p);
			}
			orderDao.updateTotal(order.getIdOrder(), totalorder);
		}
		// delete Order
		else if (path.equals("/delete.order")) {
			Long idOrder = Long.parseLong(req.getParameter("id"));
			orderitemDao.deleteOrderItems(idOrder);
			orderDao.deleteOrder(idOrder);
			resp.sendRedirect("index.order");
		}
		// Update Status Order
		else if (path.equals("/updatestatus.order") && req.getMethod().equals("POST")) {
			Long idOrder = Long.parseLong(req.getParameter("orderId"));
			Status status = Status.valueOf(req.getParameter("status"));
			orderDao.updateStatus(idOrder, status);
			resp.sendRedirect("index.order");
		}
		// show Order details
		else if (path.equals("/show.order")) {
			Long id = Long.parseLong(req.getParameter("id"));
			List<orderDetailsModel> orderDetails = orderitemDao.getOrderItemsByIdOrder(id);
			req.setAttribute("orderDetails", orderDetails);
			req.getRequestDispatcher("showorder.jsp").forward(req, resp);
		}
		// error page not found
		else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
