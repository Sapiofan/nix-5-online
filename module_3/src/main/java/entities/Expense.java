package entities;

import javax.persistence.*;

@Entity
@Table(name = "expenses")
public class Expense extends Category {
    public Expense(){}
}
