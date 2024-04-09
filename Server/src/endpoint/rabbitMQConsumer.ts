import * as amqp from 'amqplib';
import { MessageProcessor } from './messageProcessor';

class RabbitMQConsumer {
    private readonly uri: string;
    private readonly queueName: string;
    private readonly messageProcessor: MessageProcessor;

    constructor(username: string, password: string, host: string, port: number, queueName: string) {
        this.uri = `amqp://${username}:${password}@${host}:${port}`;
        this.queueName = queueName;
        this.messageProcessor = new MessageProcessor(); // Crear una instancia de MessageProcessor


    }

    public async startConsuming(): Promise<void> {
        try {
            const connection = await amqp.connect(this.uri);
            const channel = await connection.createChannel();

            await channel.assertQueue(this.queueName, { durable: true });
            console.log(`Escuchando la cola ${this.queueName}.`);

            channel.consume(this.queueName, (message) => {
                if (message !== null) {
                    console.log(`Mensaje recibido: ${message.content.toString()}`);
                    this.messageProcessor.procesarMensaje(message.content.toString());
                    channel.ack(message);
                }
            });

        } catch (error) {
            console.error('Error al iniciar la escucha de la cola:', error);
        }
    }
}

export default RabbitMQConsumer;


