import json

import brazilcep
from flask import Flask, request, jsonify

from geopy import Nominatim

app = Flask(__name__)


@app.route('/calculate', methods=['GET'])
def resolve():
    cep_param = request.args.get('cep')
    address = brazilcep.get_address_from_cep(cep_param)
    geolocator = Nominatim(user_agent='taxi')
    coord = geolocator.geocode(address['street'] + ", " + address['city'] + " - " + address['district'])
    return jsonify(
        {
            "cep": cep_param,
            "coordName": coord.address,
            "latitude": coord.latitude,
            "longitude": coord.longitude
        }
    )


if __name__ == '__main__':
    app.run(port=5001)
