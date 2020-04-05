package main.repository;

import main.model.GlobalSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GlobalSettingsRepository extends JpaRepository<GlobalSettings, Integer> {

    @Query(value = "SELECT * FROM global_settings gl WHERE gl.code = ?1 ", nativeQuery = true)
    List<GlobalSettings> codeList(String code);
}
