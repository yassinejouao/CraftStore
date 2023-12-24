package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.IProductDao;
import dao.ProductDaoImpl;
import metier.entities.Product;
import metier.models.ProductModel;

@WebServlet(name = "cs", urlPatterns = { "*.product" })
public class ControllerProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProductDao productDao;

	@Override
	public void init() throws ServletException {
		productDao = new ProductDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		// index product page
		if (path.equals("/index.product")) {
			ProductModel productModel = new ProductModel();
			productModel.setKeyword("");
			List<Product> products = productDao.searchProductByKeyWord("%%");
			productModel.setProducts(products);
			req.setAttribute("productModel", productModel);
			req.getRequestDispatcher("products.jsp").forward(req, resp);
		}
		// search for product
		else if (path.equals("/search.product")) {
			String keyword = req.getParameter("Keyword");
			ProductModel productModel = new ProductModel();
			productModel.setKeyword(keyword);
			List<Product> products = productDao.searchProductByKeyWord("%" + keyword + "%");
			productModel.setProducts(products);
			req.setAttribute("productModel", productModel);
			req.getRequestDispatcher("products.jsp").forward(req, resp);

		}
		// add product
		else if (path.equals("/add.product")) {
			req.getRequestDispatcher("addProduct.jsp").forward(req, resp);
		}
		// save product
		else if (path.equals("/save.product") && req.getMethod().equals("POST")) {
			String nameProduct = req.getParameter("nameProduct");
			Long stock = Long.parseLong(req.getParameter("stock"));
			Double price = Double.parseDouble(req.getParameter("priceProduct"));
			Product p = productDao.save(new Product(nameProduct, price, stock));
			if (p == null) {
				req.getRequestDispatcher("products.jsp").forward(req, resp);
			}
			req.setAttribute("message", "product " + p.getNameProduct() + " has been added successfully");
			req.getRequestDispatcher("confirmation.jsp").forward(req, resp);

		}
		// delete product
		else if (path.equals("/delete.product")) {
			Long id = Long.parseLong(req.getParameter("id"));
			productDao.deleteProduct(id);
			resp.sendRedirect("index.product");
		}
		// edit product
		else if (path.equals("/edit.product")) {
			Long id = Long.parseLong(req.getParameter("id"));
			Product p = productDao.getProduct(id);
			req.setAttribute("product", p);
			req.getRequestDispatcher("editProduct.jsp").forward(req, resp);
		}
		// update product
		else if (path.equals("/update.product") && req.getMethod().equals("POST")) {
			Long idProduct = Long.parseLong(req.getParameter("idProduct"));
			String nameProduct = req.getParameter("nameProduct");
			Long stock = Long.parseLong(req.getParameter("stock"));
			Double price = Double.parseDouble(req.getParameter("priceProduct"));
			Product p = new Product(nameProduct, price, stock);
			p.setIdProduct(idProduct);
			productDao.updateProduct(p);
			req.setAttribute("message", "product has been updated successfully");
			req.getRequestDispatcher("confirmation.jsp").forward(req, resp);
		}
		// error page not found
		else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
