import NotificationButton from '../NotificationButton'
//import DatePicker
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import './styles.css'
import { useEffect, useState } from 'react';
import axios from 'axios';
import { Sale } from '../../models/Sale';
import { BASE_URL } from '../../utils/request';

function SalesCard() {

  // Declaração de Estado para um componente
  // const min = new Date(new Date().setDate(new Date().getDate() - 365)); //para pegar data de 1 ano antes do ano vigente
  const [minDate, setMinDate] = useState(new Date());
  const [maxDate, setMaxDate] = useState(new Date());
  // useState para armazenar a lista de vendas com nome do dado (sales) e  o nome da função que altera o dado(setSales)
  // useState<Sale[]([])>  NOTA: Forma de fazer um useState ser do tipo lista com valor inicial uma lista vazia -> ([])
  const [sales, setSales] = useState<Sale[]>([]);


  useEffect(() => {
    axios.get(`${BASE_URL}/Sales`)// --> Faz uma requisição para o backEnd trazendo através da variável de ambiente  "http://localhost:8080"
      .then(response => {
        // console.log(response.data)
        setSales(response.data.content);
      })
  }, []);

  return (
    <div className="dsmeta-card">
      <h2 className="dsmeta-sales-title">Vendas</h2>
      <div>
        <div className="dsmeta-form-control-container">
          <DatePicker
            selected={minDate}
            onChange={(date: Date) => setMinDate(date)}
            className="dsmeta-form-control"
            dateFormat="dd/MM/yyyy"
          />
        </div>
        <div className="dsmeta-form-control-container">
          <DatePicker
            selected={maxDate}
            onChange={(date: Date) => setMaxDate(date)}
            className="dsmeta-form-control"
            dateFormat="dd/MM/yyyy"
          />
        </div>
      </div>

      <div>
        <table className="dsmeta-sales-table">
          <thead>
            <tr>
              <th className="show992">ID</th>
              <th className="show576">Data</th>
              <th>Vendedor</th>
              <th className="show992">Visitas</th>
              <th className="show992">Vendas</th>
              <th>Total</th>
              <th>Notificar</th>
            </tr>
          </thead>
          <tbody
          /*
          //Expressão React
          // NOTA: .map serve para percorrer a lista e fazer operações para cada elemento da lista
          // NOTA: Quando é feito uma redentização de conteúdo baseado em um lista cada elemento precisa ter um valor único de uma Key. (key = {})
          // new Date(sale.date).toLocaleDateString() --> Data formatada
          // {sale.amount.toFixed(2)} --> valor formatado
          */
          >
            {sales.map(sale => {
              return (
                <tr key={sale.id}>
                  <td className="show992">{sale.id}</td>
                  <td className="show576">{new Date(sale.date).toLocaleDateString()}</td>
                  <td>{sale.sellerName}</td>
                  <td className="show992">{sale.visited}</td>
                  <td className="show992">{sale.deals}</td>
                  <td>R$ {sale.amount.toFixed(2)}</td>
                  <td>
                    <div className="dsmeta-red-btn-container">
                      <NotificationButton />
                    </div>
                  </td>
                </tr>
              )
            })}
          </tbody>
        </table>
      </div>

    </div>

  )

}

export default SalesCard
