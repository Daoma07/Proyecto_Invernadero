import * as amqp from 'amqplib';
import { MessageProcessor } from './messageProcessor';

class RabbitMQConsumer {
    private readonly uri: string;
    private readonly exchangeName: string;
    private readonly routingKey: string;
    private readonly messageProcessor: MessageProcessor;

    constructor(username: string, password: string, host: string, port: number,
        exchangeName: string, routingKey: string) {
        this.uri = `amqp://${username}:${password}@${host}:${port}`;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.messageProcessor = new MessageProcessor();
    }

    public async startConsuming(): Promise<void> {
        try {
            const connection = await amqp.connect(this.uri);
            const channel = await connection.createChannel();

            await channel.assertExchange(this.exchangeName, 'topic', { durable: false });
            const queue = await channel.assertQueue('', { exclusive: false });

            console.log(`Escuchando el topic '${this.routingKey}'.`);

            await channel.bindQueue(queue.queue, this.exchangeName, this.routingKey);

            channel.consume(queue.queue, (message) => {
                if (message !== null) {
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


