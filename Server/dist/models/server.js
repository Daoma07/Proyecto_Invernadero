"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = __importDefault(require("express"));
const connection_1 = __importDefault(require("../db/connection"));
const associations_1 = require("./associations");
const rabbitMQConsumer_1 = __importDefault(require("../endpoint/rabbitMQConsumer"));
class Server {
    constructor() {
        this.app = (0, express_1.default)();
        this.port = process.env.PORT || '3001';
        this.dbConnct();
        this.startMessageConsumer();
    }
    listen() {
        this.app.listen(this.port, () => {
            console.log("Servidor coerriendo en el puerto " + this.port);
        });
    }
    startMessageConsumer() {
        const messageConsumer = new rabbitMQConsumer_1.default('root', '1234', 'localhost', 5672, 'server');
        messageConsumer.startConsuming()
            .then(() => console.log('Conexión establecida.'))
            .catch(error => console.error('Error al establecer la conexión:', error));
    }
    dbConnct() {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                (0, associations_1.initializeRelationships)();
                yield connection_1.default.sync();
            }
            catch (error) {
                console.log("No se pudo conectar a la bd", error);
            }
        });
    }
}
exports.default = Server;
