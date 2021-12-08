package bsuir.wt.lab4.service;

import bsuir.wt.lab4.service.impl.BookingServiceImpl;
import bsuir.wt.lab4.service.impl.UsersServiceImpl;
import bsuir.wt.lab4.service.impl.RoomsServiceImpl;

public class ServiceProvider {

	private static final ServiceProvider instance = new ServiceProvider();

	private final UsersService clientService = new UsersServiceImpl();
	private final RoomsService roomsService = new RoomsServiceImpl();
	private final BookingService bookingService = new BookingServiceImpl();

	private ServiceProvider() {}
	
	public UsersService getClientService() {
		return clientService;
	}

	public RoomsService getRoomsService() {
		return roomsService;
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public BookingService getBookingService() {
		return bookingService;
	}
}
