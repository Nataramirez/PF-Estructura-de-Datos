package org.acme.model.enums.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum BookState {

    AVAILABLE("AVAILABLE"),
    LOANED("LOANED");

    private String value;

}
