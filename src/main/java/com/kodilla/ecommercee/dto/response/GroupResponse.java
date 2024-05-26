package com.kodilla.ecommercee.dto.response;

import java.util.List;

public record GroupResponse(Long id, String name, List<Long> productsId) {
}
