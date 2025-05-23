package org.acme.model.enums.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum BookCategory {

    ART("ART"),
    FANTASY("FANTASY"),
    HISTORY("HISTORY"),
    MYSTERY("MYSTERY"),
    ROMANCE("ROMANCE"),
    SCIENCE_FICTION("SCIENCE_FICTION"),
    COOKING("COOKING");

    private String value;

    public static boolean isValidCategory(String category) {
        for (BookCategory bookCategory : BookCategory.values()) {
            if (bookCategory.getValue().equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }

}
