package deviceAssignment;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import deviceAssignment.deviceAssigned.DeviceAssignedService;
import deviceAssignment.devices.DeviceService;
import deviceAssignment.devices.DeviceState;
import deviceAssignment.devices.DeviceType;
import deviceAssignment.users.UsersService;
import deviceAssignment.userspayloads.UserRequestPayload;
import deviceAssignment.devices.payloads.DeviceRequestPayload;

@Component
public class Runner implements CommandLineRunner{

	@Autowired
	UsersService userServ;
	
	@Autowired
	DeviceService deviceServ;
	
	@Autowired
	DeviceAssignedService devicesAssignedServ;
	
	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		for (int i = 0; i < 8; i++) {
			String name = faker.name().firstName();
			String surname = faker.name().lastName();
			String email = faker.internet().emailAddress();
			String username = (name + surname).toLowerCase().trim();
			String password = "1234";
			UserRequestPayload user = new UserRequestPayload(name, surname, email, password, username);
			userServ.create(user);
		}
		
		DeviceRequestPayload device1 = new DeviceRequestPayload(DeviceState.ASSIGNED,
				DeviceType.LAPTOP);
		DeviceRequestPayload device2 = new DeviceRequestPayload(DeviceState.DISCONTINUED,
				DeviceType.SMARTPHONE);
		DeviceRequestPayload device3 = new DeviceRequestPayload(DeviceState.AVAILABLE,
				DeviceType.TABLET);
		DeviceRequestPayload device4 = new DeviceRequestPayload(DeviceState.MAINTENANCE,
				DeviceType.SMARTPHONE);

		deviceServ.create(device1);
		deviceServ.create(device2);
		deviceServ.create(device3);
		deviceServ.create(device4);
		
	}

}
