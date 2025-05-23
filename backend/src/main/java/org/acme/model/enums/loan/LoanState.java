package org.acme.model.enums.loan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum LoanState {

    WAITING("WAITING"),
    RETURNED("RETURNED"),
    LOANED("LOANED");

    private String value;
}
