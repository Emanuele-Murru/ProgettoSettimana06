package deviceAssignment.devices;

import java.util.UUID;

import deviceAssignment.users.User;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Device {
	@Id
	@GeneratedValue
	private UUID id;
	@Convert(converter = Converter.class)
	private String deviceModel;
	@Enumerated(EnumType.STRING)
	private DeviceState deviceState;
	@Enumerated(EnumType.STRING)
	private DeviceType deviceType;
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	public Device(DeviceState deviceState, DeviceType deviceType) {
		this.deviceState = deviceState;
		this.deviceType = deviceType;
	}

}
