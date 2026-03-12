package securitymake;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OtpRepository otpRepository;

	public void addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public void auth(User user) {
		User targetUser = userRepository.findUserByUsername(user.getUsername())
			.orElseThrow(() -> new BadCredentialsException("Bad credentials"));

		if (!passwordEncoder.matches(user.getPassword(), targetUser.getPassword())) {
			throw new BadCredentialsException("Bad credentials");
		}

		renewOtp(targetUser);
	}

	public boolean check(Otp otpToValidate) {
		return otpRepository.findOtpByUsername(otpToValidate.getUsername())
			.filter(otp -> otp.getCode().equals(otpToValidate.getCode()))
			.isPresent();
	}

	private void renewOtp(User targetUser) {
		String code = GenerateCodeUtil.generateCode();

		Optional<Otp> userOtp = otpRepository.findOtpByUsername(targetUser.getUsername());

		if (userOtp.isPresent()) {
			Otp otp = userOtp.get();
			otp.setCode(code);
		} else {
			Otp otp = new Otp();
			otp.setUsername(targetUser.getUsername());
			otp.setCode(code);
			otpRepository.save(otp);
		}
	}
}
