package cars.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Logger.Log;
import cars.entity.Car;
import utils.Error;
import utils.HibernateUtil;

@Stateless
public class Controller {

	public Response response;
	public Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction transaction = session.beginTransaction();

	public Response getAllCars() {

		try {
			List<Car> cars = session.createQuery("from Car").list();
			Car[] arrayCars = cars.toArray(new Car[cars.size()]);
			session.getTransaction().commit();
			if (arrayCars.length != 0) {
				response = Response.status(200).entity(arrayCars).build();
			} else {
				response = Response.status(404).entity(new Error(Error.nullResource)).build();
			}
		} catch (Exception e) {
			Log.logger.warning(e.getMessage());
			response = Response.status(500).build();
			session.getTransaction().commit();
		}

		return response;
	}

	public Response getCar(final int carId) {


		try {
			Car car = (Car) session.get(Car.class, carId);
			if (car != null) {
				response = Response.status(200).entity(car).build();
			} else {
				response = Response.status(404).build();
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			Log.logger.warning(e.getMessage());
			e.printStackTrace();
			response = Response.status(500).build();
			session.getTransaction().commit();
		}

		return response;
	}

	public Response postCar(final Car car) {


		try {
			session.save(car);
			session.getTransaction().commit();
			response = Response.status(201).entity(car).build();
		} catch (Exception e) {
			// Removes the bad query
			session.clear();
			response = Response.status(400).build();
			// Do an empty commit in order to avoid nested transactions
			session.getTransaction().commit();
		}

		return response;
	}

	public Response updateCar(final Car newData, final int carId) {

		Car carToUpdate = null;

		try {
			carToUpdate = (Car) session.get(Car.class, carId);
		} catch (Exception e) {
			Log.logger.warning(e.getMessage());
			e.printStackTrace();
			response = Response.status(500).build();
			session.clear();
		}

		if (carToUpdate != null) {
			carToUpdate.copyValuesFrom(newData);

			try {
				session.update(carToUpdate);
				session.getTransaction().commit();
				response = Response.status(200).build();
			} catch (Exception e) {
				e.printStackTrace();
				Log.logger.warning(e.getMessage());
				response = Response.status(400).build();
				session.getTransaction().rollback();
				session.clear();
			}
		} else {
			response = Response.status(404).build();
			session.getTransaction().commit();
		}

		return response;
	}

	public Response deleteCar(final int carId) {


		Car car = (Car) session.get(Car.class, carId);
		if (car != null) {
			try {
				session.delete(car);
				session.getTransaction().commit();
				response = Response.status(204).build();
			} catch (Exception e) {
				e.printStackTrace();
				Log.logger.warning(e.getMessage());
				response = Response.status(404).entity((new Error(Error.deleteNullResource))).build();
				session.getTransaction().commit();
			}
		} else {
			response = Response.status(404).build();
			session.getTransaction().commit();
		}

		return response;
	}

}
