package com.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name="Value_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Value {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long value;

    @Override
    public String toString() {
        return "Value{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
