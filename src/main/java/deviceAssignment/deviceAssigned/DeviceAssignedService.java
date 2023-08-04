package deviceAssignment.deviceAssigned;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import deviceAssignment.devices.Device;
import deviceAssignment.devices.DeviceRepository;
import deviceAssignment.users.User;
import deviceAssignment.users.UserRepository;

@Service
public class DeviceAssignedService {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private DeviceRepository devRepo;

	public void assignDevice(UUID userId, UUID deviceId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("Utente non trovato con ID: " + userId));
		Device device = devRepo.findById(deviceId)
				.orElseThrow(() -> new IllegalArgumentException("Dispositivo non trovato con ID: " + deviceId));
		device.setUser(user);
		devRepo.save(device);
	}

}