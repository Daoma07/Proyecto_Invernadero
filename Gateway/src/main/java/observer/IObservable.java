/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package observer;

/**
 *
 * @author Daniel
 */
public interface IObservable {

    public void actualizarTodos();

    public void agregarObservador(IObserver observador);
}
