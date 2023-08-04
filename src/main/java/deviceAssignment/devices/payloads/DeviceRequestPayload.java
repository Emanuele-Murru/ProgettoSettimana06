package deviceAssignment.devices.payloads;

import deviceAssignment.devices.DeviceState;
import deviceAssignment.devices.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeviceRequestPayload {
	private DeviceState deviceState;
	private DeviceType DeviceType;
}
