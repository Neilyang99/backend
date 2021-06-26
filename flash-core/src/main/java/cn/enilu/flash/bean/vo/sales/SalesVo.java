package cn.enilu.flash.bean.vo.sales;

import lombok.Data;

@Data
/**
 * SalesVo
 *
 * @author YWG
 */
public class SalesVo {
    private String key;
    private String value;

    public SalesVo() {
    }
    public SalesVo(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
