import { Data } from '../models/data';
import { Sensor, SensorModel } from '../models/sensor';

export class MessageProcessor {
    public async procesarMensaje(mensajeJSON: string): Promise<void> {
        try {
            const mensajes = JSON.parse(mensajeJSON);
            for (const mensaje of mensajes) {

                const { serie, date, data, gateway } = mensaje;



                let sensor: SensorModel | null = await Sensor.findOne({ where: { serie, gateway } });


                if (!sensor) {

                    sensor = await Sensor.create({ serie, gateway });
                }

                const idDelSensor: number | undefined = sensor?.getDataValue("id");

                if (idDelSensor === undefined) {
                    throw new Error("No se pudo obtener el ID del sensor.");
                }


                const datosPromesas = data.map(async ({ type, magnitude, value }: { type: string, magnitude: string, value: number }) => {
                    await Data.create({
                        type,
                        magnitude,
                        value,
                        date,
                        id_sensor: idDelSensor
                    });
                });

                await Promise.all(datosPromesas);


            }

            console.log("Datos procesados correctamente.");

        } catch (error) {
            console.error('Error al procesar el mensaje:', error);
        }
    }
}

