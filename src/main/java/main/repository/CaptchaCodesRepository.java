package main.repository;

import main.model.CaptchaCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface CaptchaCodesRepository extends JpaRepository<CaptchaCodes, Integer> {

    @Query(value = "SELECT * FROM captcha_codes c WHERE c.code = ?1 AND c.secret_code = ?2 ", nativeQuery = true)
    public List<CaptchaCodes> newUserCaptcha(String captcha, String captchaSecret);
}
