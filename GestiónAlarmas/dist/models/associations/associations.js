"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.initializeRelationships = void 0;
const connection_1 = __importDefault(require("../../db/connection"));
const alarm_1 = require("../alarm");
const contact_1 = require("../contact");
const data_1 = require("../data");
const greenhouse_1 = require("../greenhouse");
const sensor_1 = require("../sensor");
const threshold_1 = require("../threshold");
const SensorThreshold_1 = require("../SensorThreshold");
const sequelize_1 = require("sequelize");
function initializeRelationships() {
    alarm_1.Alarm.belongsTo(sensor_1.Sensor, { foreignKey: 'id_sensor' });
    sensor_1.Sensor.hasMany(alarm_1.Alarm, { foreignKey: 'id_sensor' });
    sensor_1.Sensor.hasMany(data_1.Data, { foreignKey: 'id_sensor' });
    data_1.Data.belongsTo(sensor_1.Sensor, { foreignKey: 'id_sensor' });
    sensor_1.Sensor.belongsTo(greenhouse_1.Greenhouse, { foreignKey: 'id_Greenhouse' });
    greenhouse_1.Greenhouse.hasMany(sensor_1.Sensor, { foreignKey: 'id_Greenhouse' });
    sensor_1.Sensor.belongsToMany(threshold_1.Threshold, { through: SensorThreshold_1.SensorThreshold });
    threshold_1.Threshold.belongsToMany(sensor_1.Sensor, { through: SensorThreshold_1.SensorThreshold });
    const GreenhouseContact = connection_1.default.define('greenhouse_contact', {
        id: {
            type: sequelize_1.DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true
        }
    }, {
        timestamps: false
    });
    greenhouse_1.Greenhouse.belongsToMany(contact_1.Contact, { through: GreenhouseContact });
    contact_1.Contact.belongsToMany(greenhouse_1.Greenhouse, { through: GreenhouseContact });
}
exports.initializeRelationships = initializeRelationships;
