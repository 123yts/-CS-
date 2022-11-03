package com.yts.system.view.handler;

import com.yts.system.constant.BookConstant;
import com.yts.system.controller.BookController;
import com.yts.system.net.TCPClient;
import com.yts.system.util.Protocol;
import com.yts.system.view.MainView;
import com.yts.system.view.page.BookUpdateView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookUpdateViewHandler implements ActionListener {

    private BookUpdateView bookUpdateView;
    private MainView mainView;
    private BookController bookController = new BookController();

    public BookUpdateViewHandler(MainView mainView, BookUpdateView bookUpdateView){
        this.bookUpdateView = bookUpdateView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("修改".equals(text)){
//            TCPClient tcpConnection = TCPClient.getConnection();
//            String response = tcpConnection.request(Protocol.updateBookRequest(bookUpdateView.getBook()));
            String response = bookController.updateBookByNumber(bookUpdateView.getBook());
            if(BookConstant.SUCCESS.equals(response)){
                mainView.reloadView();
                JOptionPane.showMessageDialog(bookUpdateView, "修改成功！");
                bookUpdateView.dispose();
            }
            else{
                JOptionPane.showMessageDialog(bookUpdateView, "修改失败！");
            }
        }
    }
}
