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
Object.defineProperty(exports, "__esModule", { value: true });
exports.MessageProcessor = void 0;
const data_1 = require("../models/data");
const sensor_1 = require("../models/sensor");
class MessageProcessor {
    procesarMensaje(mensajeJSON) {
        return __awaiter(this, void 0, void 0, function* () {
            try {
                const mensaje = JSON.parse(mensajeJSON);
                const { serie, date, data, gateway } = mensaje;
                let sensor = yield sensor_1.Sensor.findOne({ where: { serie, gateway } });
                if (!sensor) {
                    sensor = yield sensor_1.Sensor.create({ serie, gateway });
                }
                const idDelSensor = sensor === null || sensor === void 0 ? void 0 : sensor.getDataValue("id");
                if (idDelSensor === undefined) {
                    throw new Error("No se pudo obtener el ID del sensor.");
                }
                const datosPromesas = data.map((_a) => __awaiter(this, [_a], void 0, function* ({ type, magnitude, value }) {
                    yield data_1.Data.create({
                        type,
                        magnitude,
                        value,
                        date,
                        id_sensor: idDelSensor
                    });
                }));
                yield Promise.all(datosPromesas);
                console.log("Datos procesados correctamente.");
            }
            catch (error) {
                console.error('Error al procesar el mensaje:', error);
            }
        });
    }
}
exports.MessageProcessor = MessageProcessor;
