package com.telegram.bot.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Semion Rutshtein
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Integer id;
    private String userName;

}
