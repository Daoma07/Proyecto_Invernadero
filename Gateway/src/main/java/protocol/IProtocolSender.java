/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package protocol;

/**
 *
 * @author Daniel
 */
public interface IProtocolSender {

    public void connect();

    public void send(String message);
}
