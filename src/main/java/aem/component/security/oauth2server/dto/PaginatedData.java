package aem.component.security.oauth2server.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PaginatedData<T> {
    private int size;
    private int page;
    private long total;
    private List<T> content;

    public PaginatedData(Page<T> page) {
        this.size = page.getSize();
        this.page = page.getPageable().getPageNumber();
        this.content = page.getContent();
        this.total = page.getTotalElements();
    }
}
