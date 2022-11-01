package com.yts.system.view.handler;

import com.yts.system.constant.BookConstant;
import com.yts.system.model.Book;
import com.yts.system.net.TCPClient;
import com.yts.system.util.Parser;
import com.yts.system.util.Protocol;
import com.yts.system.view.MainView;
import com.yts.system.view.page.BookAddView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookAddViewHandler implements ActionListener {

    private BookAddView bookAddView;
    private MainView mainView;

    public BookAddViewHandler(MainView mainView, BookAddView bookAddView){
        this.bookAddView = bookAddView;
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if ("添加".equals(text)){
//            BookService bookService = new BookService();
//            Book book = bookAddView.getBook();
//            if (bookService.getBookByNumber(book.getNumber()) != null){
//                JOptionPane.showMessageDialog(bookAddView, "该书已收录在系统中，请勿重复添加！");
//                return;
//            }
//            boolean result = new BookService().addBook(book);
            //Todo
            TCPClient tcpConnection = TCPClient.getConnection();

            String response = tcpConnection.request(Protocol.queryBookByNumberRequest(bookAddView.getBook().getNumber()));
//            System.out.println("比较：" + BookConstant.FAILURE.equals(response));
//            System.out.println("find： " + response);
            if (!BookConstant.FAILURE.equals(response)){
                JOptionPane.showMessageDialog(bookAddView, "该书已收录在系统中，请勿重复添加！");
                return;
            }

            response = tcpConnection.request(Protocol.addBookRequest(bookAddView.getBook()));
            if(BookConstant.SUCCESS.equals(response)){
                //刷新主界面
                mainView.reloadView();
                JOptionPane.showMessageDialog(bookAddView, "添加成功！");
                bookAddView.dispose();
            }
            else{
                JOptionPane.showMessageDialog(bookAddView, "添加失败！");
            }
        }
    }
}
