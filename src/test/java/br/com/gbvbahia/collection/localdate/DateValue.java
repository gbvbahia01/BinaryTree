package br.com.gbvbahia.collection.localdate;

import br.com.gbvbahia.collection.BinaryTreeValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class DateValue implements BinaryTreeValue<DateValue> {

    private final String ticket;
    private final LocalDate dataBase;
    private final Double value;


    @Override
    public int compareTo(DateValue o) {
        return dataBase.compareTo(o.dataBase);
    }

}
