package com.example.todolist;

import androidx.test.InstrumentationRegistry;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.example.todolist.local_data.DatabaseHelper;
import com.example.todolist.to_do.model.ToDoModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DatabaseHelperTest {
    private DatabaseHelper databaseHelper;

    @Before
    public void setUp() {
        databaseHelper = new DatabaseHelper(InstrumentationRegistry.getTargetContext());
    }

    @Test
    public void testPreConditions() {
        assertNotNull(databaseHelper);
    }

    @Test
    public void insertToDoColumn() throws Exception {
        ToDoModel toDoModel = new ToDoModel();
        toDoModel.setCardTitle("title");
        toDoModel.setCardDescription("description");
        toDoModel.setCardMail("test@mail.com");
        databaseHelper.insertToDoColumn(toDoModel);
        ArrayList<ToDoModel> toDoList = databaseHelper.getToDoList();

        assertEquals("title", toDoList.get(0).getCardTitle());
        assertEquals("description", toDoList.get(0).getCardDescription());
        assertEquals("test@mail.com", toDoList.get(0).getCardMail());
        assertEquals(1, toDoList.get(0).getId());
    }

    @Test
    public void deleteToDoModel() throws Exception {
        ArrayList<ToDoModel> toDoList = databaseHelper.getToDoList();
        ToDoModel toDoModel = new ToDoModel();
        toDoModel.setCardTitle("title");
        toDoModel.setCardDescription("description");
        toDoModel.setCardMail("test@mail.com");
        //set exist id to delete it
        toDoModel.setId(3);
        databaseHelper.deleteToDoModel(toDoModel);

        assertEquals(toDoList.size() - 1, databaseHelper.getToDoList().size());
    }

    @Test
    public void deleteTable() throws Exception {

        databaseHelper.deleteTable();

        assertEquals(null, databaseHelper.getToDoList());
    }



}
