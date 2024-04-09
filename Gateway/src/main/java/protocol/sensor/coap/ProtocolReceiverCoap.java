/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package protocol.sensor.coap;

import gateway.IGateway;
import message_process.IMessageProcess;
import message_process.MessageProcessCoap;
import protocol.IProtocolReceiver;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
 *
 * @author Daniel
 */
public class ProtocolReceiverCoap implements IProtocolReceiver {

    private final CoapServer coapServer;
    private final String resource;
    private transient final IMessageProcess messageProcess
            = new MessageProcessCoap();
    private IGateway gateway;

    public ProtocolReceiverCoap(String resorce) {
        this.resource = resorce;
        coapServer = new CoapServer();
    }

    @Override
    public void subscribe() {
        coapServer.add(new CoapResource(resource) {
            @Override
            public void handlePOST(CoapExchange exchange) {
                String payload = exchange.getRequestText();
                System.out.println("Mensaje recibido del sensor: " + payload);
                processMessage(payload);
                exchange.respond(CoAP.ResponseCode.CREATED);
            }
        });
    }

    private void processMessage(String message) {
        String proccesMessage = messageProcess.messageFormat(message);
        gateway.processMessage(proccesMessage);
    }

    @Override
    public void connect() {
        coapServer.start();
        System.out.println("Servidor CoAP encendido");
    }

    @Override
    public void desconnect() {
        coapServer.stop();
        System.out.println("Servidor CoAP detenido");
    }

    public void setGateway(IGateway gateway) {
        this.gateway = gateway;
    }

}
