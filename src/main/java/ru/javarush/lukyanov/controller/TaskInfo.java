package ru.javarush.lukyanov.controller;

import lombok.Data;
import ru.javarush.lukyanov.enums.Status;

@Data
public class TaskInfo {

    private String description;
    private Status status;
}
