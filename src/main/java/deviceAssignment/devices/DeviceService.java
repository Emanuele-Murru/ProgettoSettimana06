package deviceAssignment.devices;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import deviceAssignment.devices.payloads.DeviceRequestPayload;
import deviceAssignment.exceptions.NotFoundException;

@Service
public class DeviceService {
	private final DeviceRepository devRepo;

	@Autowired
	private DeviceService(DeviceRepository devRepo) {
		super();
		this.devRepo = devRepo;
	}

	public Device create(DeviceRequestPayload body) {

		Device newDevice = new Device(body.getDeviceState(), body.getDeviceType());
		return devRepo.save(newDevice);
	}

	public Page<Device> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return devRepo.findAll(pageable);
	}

	public Device findById(UUID id) throws NotFoundException {
		return devRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public Device findByIdAndUpdate(UUID id, DeviceRequestPayload body) throws NotFoundException {
		Device found = this.findById(id);
		found.setDeviceState(body.getDeviceState());
		found.setDeviceType(body.getDeviceType());
		return devRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Device found = this.findById(id);
		devRepo.delete(found);
	}

}