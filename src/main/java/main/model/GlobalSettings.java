package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GlobalSettings { // глобальные настройки движка

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String code; // системное имя настройки
    private String name; // название настройки
    private String value; // значение настройки

    /*          Значения глобальных настроек:

              code                        name                        value
         MULTIUSER_MODE          Многопользовательский режим         YES / NO
         POST_PREMODERATION      Премодерация постов                 YES / NO
         STATISTICS_IS_PUBLIC    Показывать всем статистику блога    YES / NO
     */


    public GlobalSettings(int id, String code, String name, String value) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.value = value;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

}
