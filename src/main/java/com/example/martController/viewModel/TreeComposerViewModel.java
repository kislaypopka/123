package com.example.martController.viewModel;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

public class TreeComposerViewModel extends SelectorComposer<Component> {

    @Wire("#tree")
    private Tree tree;

    @Wire("#addButton")
    private Button addButton;

    @Listen("onClick = #addButton")
    public void onAddButtonClicked() {
        // Открытие окна для добавления новой строки
        Window addWindow = (Window) Executions.createComponents("~./zul/addWindow.zul", null, null);
        addWindow.doModal();

        // Обработчик события "сохранить"
        addWindow.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
            @Override
            public void onEvent(Event event) {
                if (event.getTarget().getId().equals("saveButton")) {
                    // Получение текста из поля ввода в окне
                    Textbox newText = (Textbox) addWindow.getFellow("newText");
                    String text = newText.getValue();

                    // Добавление новой строки в дерево
                    Treeitem newItem = new Treeitem();
                    newItem.setLabel(text);
                    tree.getTreechildren().appendChild(newItem);

                    // Закрытие окна
                    addWindow.detach();
                }
            }
        });
    }
}