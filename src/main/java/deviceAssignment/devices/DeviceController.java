package deviceAssignment.devices;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import deviceAssignment.deviceAssigned.DeviceAssignedService;
import deviceAssignment.devices.payloads.DeviceRequestPayload;

@RestController
@RequestMapping("/devices")
public class DeviceController {
	private final DeviceService deviceService;
	private final DeviceAssignedService deviceAssignedService;

	@Autowired
	private DeviceController(DeviceService deviceService,
			DeviceAssignedService deviceAssignedService) {
		super();
		this.deviceService = deviceService;
		this.deviceAssignedService = deviceAssignedService;
	}

	@GetMapping
	public Page<Device> getDevices(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return deviceService.find(page, size, sortBy);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Device saveDevice(@RequestBody DeviceRequestPayload body) {
		Device created = deviceService.create(body);

		return created;
	}

	@GetMapping("/{deviceId}")
	public Device findById(@PathVariable UUID userId) {
		return deviceService.findById(userId);

	}

	@PutMapping("/{deviceId}")
	public Device updateDevice(@PathVariable UUID userId, @RequestBody DeviceRequestPayload body) {
		return deviceService.findByIdAndUpdate(userId, body);
	}

	@DeleteMapping("/{deviceId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDevice(@PathVariable UUID userId) {
		deviceService.findByIdAndDelete(userId);
	}

	@PostMapping("/{deviceId}/assign/{userId}")
	public ResponseEntity<String> assignUserDevice(@PathVariable UUID deviceId,
			@PathVariable UUID utenteId) {
		deviceAssignedService.assignDevice(utenteId, deviceId);
		return ResponseEntity.ok("Device successfully assigned to the user.");
	}

}
