import express, { Application } from 'express';
import sequelize from "../db/connection";
import { initializeRelationships } from './associations';
import RabbitMQConsumer from '../endpoint/rabbitMQConsumer';


class Server {
    private app: express.Application;
    private port: string;

    constructor() {
        this.app = express();
        this.port = process.env.PORT || '3001';
        this.dbConnct();
        this.startMessageConsumer();
    }

    listen() {
        this.app.listen(this.port, () => {
            console.log("Servidor coerriendo en el puerto " + this.port);
        })
    }
    startMessageConsumer() {
        const messageConsumer = new RabbitMQConsumer('root', '1234', 'localhost', 5672, 'server', 'gateway');
        messageConsumer.startConsuming()
            .then(() => console.log('Conexión establecida.'))
            .catch(error => console.error('Error al establecer la conexión:', error));
    }


    async dbConnct() {
        try {
            initializeRelationships();
            await sequelize.sync();
        } catch (error) {
            console.log("No se pudo conectar a la bd", error)
        }
    }

}

export default Server;